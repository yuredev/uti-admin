import React from 'react';
import { Admin, Resource } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import { PatientsList, PatientCreate } from './resources/Patients';
import { MedicinesList  } from './resources/Medicines';
import { createMuiTheme } from '@material-ui/core/styles';

const dataProvider = jsonServerProvider('http://localhost:8080');

const darkTheme = createMuiTheme({
  palette: {
    secondary: {
      main: '#7159c1',
    },
  },
});

export default () => {
  return (
    <Admin theme={darkTheme} dataProvider={dataProvider}>
      <Resource name="patients" list={PatientsList} create={PatientCreate}/>
      <Resource name="medicines" list={MedicinesList} />
    </Admin>
  );
};
