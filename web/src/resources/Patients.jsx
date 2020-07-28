import React from 'react';
import {
  ArrayInput,
  SimpleFormIterator,
  List,
  Datagrid,
  TextField,
  Create,
  SimpleForm,
  TextInput,
  ArrayField,
} from 'react-admin';

const PatientsList = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      {/* campos do paciente buscado */}
      <TextField source="id" />
      <TextField source="name" />
      <TextField source="cpf" />
      <TextField source="age" />
      <TextField source="phone" />
      <TextField source="observations" />
      <TextField source="hospitalizationDate" />

      <ArrayField source="medicines">
        <Datagrid>
          <TextField source="title" />
          <TextField source="stripe" />
        </Datagrid>
      </ArrayField>

      {/* <TextField source="medicines" /> */}
    </Datagrid>
  </List>
);

const PatientCreate = (props) => (
  <Create title={<span>Insert Patient</span>} {...props}>
    <SimpleForm>
      <TextInput source="name" />
      <TextInput source="cpf" />
      <TextInput source="age" />
      <TextInput source="phone" />
      <TextInput source="observations" />
      <TextInput source="hospitalizationDate" />
      <ArrayInput source="medicines">
        <SimpleFormIterator>
          <TextInput source="id" label="id" />
          <TextInput source="title" label="title" />
          <TextInput source="stripe" label="stripe" />
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Create>
);

export { PatientsList, PatientCreate };
