import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  EditButton,
  Create,
  SimpleForm,
  TextInput
} from 'react-admin';

const PatientsList = (props) => (
  <List {...props}>
    <Datagrid>
      {/* campos do paciente buscado */}
      <TextField source="cpf" />
      <TextField source="name" />
      <TextField source="phone" />
      <TextField multiline source="observations" />
      <TextField source="hospitalizationDate" />
      <TextField source="age" />
      <TextField multiline source="medicines" />
      <TextField source="id" />
      <EditButton />
    </Datagrid>
  </List>
);

const PatientCreate = (props) => (
  <Create title={<span>Insert Patient</span>} {...props}>
    <SimpleForm>
      <TextInput source="cpf" />
      <TextInput source="name" />
      <TextInput source="phone" />
      <TextInput source="hospitalizationDate" />
      <TextInput source="observations" />
      <TextInput source="age" />
      {/*<TextInput multiline source="medicines" />*/}
    </SimpleForm>
  </Create>
);

export { PatientsList, PatientCreate };