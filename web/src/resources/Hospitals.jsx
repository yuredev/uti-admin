import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  TextInput,
  ArrayInput,
  SimpleForm,
  DateInput,
  Edit,
  Create,
  SimpleFormIterator,
} from 'react-admin';

const HospitalsList = props => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="name" />
    </Datagrid>
  </List>
);

const HospitalsCreate = props => (
  <Create title={<span>Insert Hospital </span>} {...props}>
    <SimpleForm>
      <TextInput source="name" />
      <ArrayInput label="Insert Hospital Beds" source="hospitalBeds">
        <SimpleFormIterator>
          <TextInput label="Patient Name" source="patient.name" />
          <TextInput label="Patient id" source="patient.id" />
          <TextInput label="Patient CPF" source="patient.cpf" />
          <TextInput label="Patient Age" source="patient.age" />
          <TextInput label="Patient Phone" source="patient.phone" />
          <TextInput
            label="Patient Observations"
            source="patient.observations"
          />
          <DateInput
            label="Patient Hospitalization Date"
            source="patient.hospitalizationDate"
          />
          <ArrayInput label="Insert Patient Medicines" source="patient.medicines">
            <SimpleFormIterator>
              <TextInput source="id" label="id" />
              <TextInput source="title" label="title" />
              <TextInput source="stripe" label="stripe" />
            </SimpleFormIterator>
          </ArrayInput>
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Create>
);

const HospitalsEdit = props => (
  <Edit title={<span>Edit Hospital </span>} {...props}>
    <SimpleForm>
      <TextInput source="name" />
      <ArrayInput label="Insert Hospital Beds" source="hospitalBeds">
        <SimpleFormIterator>
          <TextInput label="Patient Name" source="patient.name" />
          <TextInput label="Patient id" source="patient.id" />
          <TextInput label="Patient CPF" source="patient.cpf" />
          <TextInput label="Patient Age" source="patient.age" />
          <TextInput label="Patient Phone" source="patient.phone" />
          <TextInput
            label="Patient Observations"
            source="patient.observations"
          />
          <DateInput
            label="Patient Hospitalization Date"
            source="patient.hospitalizationDate"
          />
          <ArrayInput label="Insert Patient Medicines" source="patient.medicines">
            <SimpleFormIterator>
              <TextInput source="id" label="id" />
              <TextInput source="title" label="title" />
              <TextInput source="stripe" label="stripe" />
            </SimpleFormIterator>
          </ArrayInput>
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Edit>
);

export { HospitalsList, HospitalsCreate, HospitalsEdit };
