import {Admin, fetchUtils, ListGuesser, Resource} from "react-admin";
// @ts-ignore
import jsonHalRestProvider from 'ra-data-json-hal';
import {authProvider} from "./authProvider";
import {Dashboard} from "./Dashboard";
import * as ra_core from "ra-core";

const httpClient = (url: string, options = {} as ra_core.Options) => {
    options.credentials = "include";
    return fetchUtils.fetchJson(url, options);
}

const apiUrl = 'http://localhost:5173/api';
const dataProvider = jsonHalRestProvider(apiUrl, httpClient);

const App = () =>
    <Admin authProvider={authProvider} dataProvider={dataProvider} dashboard={Dashboard} requireAuth>
        <Resource name="testEntities" list={ListGuesser}/>
    </Admin>;

export default App;
