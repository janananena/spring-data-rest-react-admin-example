import {AuthProvider} from "react-admin";

export const authProvider: AuthProvider = {
        // called when the user attempts to log in
        login: ({username, password}) => {

            var urlencoded = new URLSearchParams();
            urlencoded.append("username", username);
            urlencoded.append("password", password);

            console.log("**** logging in " + username);
            const request = new Request('http://localhost:5173/api/login', {
                method: 'POST',
                body: urlencoded,
                headers: new Headers({'Content-Type': 'application/x-www-form-urlencoded'}),
                credentials: "include",
            });
            return fetch(request)
                .then(response => {
                    if (response.status < 200 || response.status >= 300) {
                        throw new Error(response.statusText);
                    }
                    return Promise.resolve();
                })
                .then(() => {
                    console.log("**** logged in " + username);
                    localStorage.setItem('username', JSON.stringify(username));
                })
                .catch(() => {
                    console.log("**** logging in failed!")
                    throw new Error('Network error')
                });
        },
        // called when the user clicks on the logout button
        logout: () => {
            const request = new Request('http://localhost:5173/api/logout', {
                method: 'POST',
                credentials: "include",
            });
            return fetch(request)
                .then(response => {
                    if (response.status < 200 || response.status >= 300) {
                        throw new Error(response.statusText);
                    }
                    return Promise.resolve();
                })
                .then(() => {
                    console.log("**** logged out successfully")
                    localStorage.removeItem("username");
                })
                .catch(() => {
                    console.log("**** logout failed!")
                    throw new Error('Network error')
                });
        },
        // called when the API returns an error
        checkError: ({status}) => {
            if (status === 401 || status === 403) {
                localStorage.removeItem("username");
                return Promise.reject();
            }
            return Promise.resolve();
        },
        // called when the user navigates to a new location, to check for authentication
        checkAuth: () => {
            if (localStorage.getItem("username")) {
                const request = new Request('http://localhost:5173/api/user', {
                    headers: new Headers({'Accept': 'application/hal+json'}),
                    credentials: "include",
                });
                return fetch(request)
                    .then(response => {
                        const status = response.status;
                        if (status == 401) {
                            //unauthorized
                            console.log("**** checkAuth - unauthorized")
                            localStorage.removeItem("username");
                            throw new Error(status.toString());
                        } else {
                            console.log("**** checkAuth - success")
                            return Promise.resolve();
                        }
                    }).catch(() => {
                        return Promise.reject();
                    });
            } else {
                console.log("**** checkAuth - not logged in")
                return Promise.reject();
            }
        },
        // called when the user navigates to a new location, to check for permissions / roles
        getPermissions: () => Promise.resolve
        (),
    }
;