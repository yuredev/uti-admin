import React from 'react';
import { Admin, Resource } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import { PatientsList, PatientCreate } from './resources/Patients';
import { HospitalsList, HospitalCreate } from './resources/Hospitals';
import { HospitalBedsList } from './resources/HospitalBeds';
import { MedicinesList } from './resources/Medicines';
import { createMuiTheme } from '@material-ui/core/styles';
import { FaHospital, FaUser } from 'react-icons/fa';
import { GiHealthCapsule, GiBed } from 'react-icons/gi';

const dataProvider = jsonServerProvider('http://localhost:8080');

const darkTheme = createMuiTheme({
  palette: {
    // type: 'dark',
    secondary: {
      main: '#7159c1',
    },
    primary: {
      main: '#7159c1',
    },
  },
});

export default () => {
  return (
    <Admin theme={darkTheme} dataProvider={dataProvider} title="UTI Admin">
      <Resource
        name="patients"
        list={PatientsList}
        create={PatientCreate}
        icon={FaUser}
      />
      <Resource name="medicines" list={MedicinesList} icon={GiHealthCapsule} />
      <Resource name="hospitals" list={HospitalsList} icon={FaHospital} create={HospitalCreate}/>
      <Resource
        name="hospital-beds"
        icon={GiBed}
        options={{ label: 'Beds' }}
        list={HospitalBedsList}
      />
    </Admin>
  );
};
