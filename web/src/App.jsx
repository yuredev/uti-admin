import React from 'react';
import { Admin, Resource } from 'react-admin';
import jsonServerProvider from 'ra-data-json-server';
import { PatientsList, PatientCreate, PatientEdit } from './resources/Patients';
import { HospitalsList, HospitalsCreate, HospitalsEdit } from './resources/Hospitals';
import { HospitalBedsList, HospitalBedsCreate, HospitalsBedsEdit } from './resources/HospitalBeds';
import { createMuiTheme } from '@material-ui/core/styles';
import { FaHospital, FaUser } from 'react-icons/fa';
import { GiHealthCapsule, GiBed } from 'react-icons/gi';
import {
  MedicinesList,
  MedicineCreate,
  MedicineEdit,
} from './resources/Medicines';

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
        edit={PatientEdit}
      />
      <Resource
        name="medicines"
        list={MedicinesList}
        edit={MedicineEdit}
        create={MedicineCreate}
        icon={GiHealthCapsule}
      />
      <Resource
        name="hospitals"
        list={HospitalsList}
        icon={FaHospital}
        create={HospitalsCreate}
        edit={HospitalsEdit}
      />
      <Resource
        name="hospital-beds"
        icon={GiBed}
        options={{ label: 'Beds' }}
        list={HospitalBedsList}
        create={HospitalBedsCreate}
        edit={HospitalsBedsEdit}
      />
    </Admin>
  );
};
