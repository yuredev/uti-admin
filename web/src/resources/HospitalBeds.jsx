import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  Create,
  SimpleForm,
  DateInput,
  DateField,
  SimpleFormIterator,
  ArrayInput,
  TextInput,
  Edit,
} from 'react-admin';

const HospitalBedsList = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="patient.name" label="Ocuped by" />
      <TextField source="patient.observations" label="Observations" />
      <DateField source="patient.hospitalizationDate" label="Hospitalization Date" />
    </Datagrid>
  </List>
);

const HospitalBedsCreate = (props) => (
  <Create title={<span>Insert Hospital Bed</span>} {...props}>
    <SimpleForm>
      <TextInput label="Patient Name" source="patient.name" />
      <TextInput label="Patient CPF" source="patient.cpf" />
      <TextInput label="Patient Age" source="patient.age" />
      <TextInput label="Patient Phone" source="patient.phone" />
      <TextInput label="Patient Observations" source="patient.observations" />
      <DateInput
        label="Patient Hospitalization Date"
        source="patient.hospitalizationDate"
      />
      <ArrayInput label="Patient Medicines" source="patient.medicines">
        <SimpleFormIterator>
          <TextInput source="id" label="id" />
          <TextInput source="title" label="title" />
          <TextInput source="stripe" label="stripe" />
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Create>
);

const HospitalsBedsEdit = (props) => (
  <Edit title={<span>Edit Hospital Bed</span>} {...props}>
    <SimpleForm>
      <TextInput label="Patient Name" source="patient.name" />
      <TextInput label="Patient ID" source="patient.id" />
      <TextInput label="Patient CPF" source="patient.cpf" />
      <TextInput label="Patient Age" source="patient.age" />
      <TextInput label="Patient Phone" source="patient.phone" />
      <TextInput label="Patient Observations" source="patient.observations" />
      <DateInput
        label="Patient Hospitalization Date"
        source="patient.hospitalizationDate"
      />
      <ArrayInput label="Patient Medicines" source="patient.medicines">
        <SimpleFormIterator>
          <TextInput source="id" label="id" />
          <TextInput source="title" label="title" />
          <TextInput source="stripe" label="stripe" />
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Edit>
);

export { HospitalBedsList, HospitalBedsCreate, HospitalsBedsEdit };
