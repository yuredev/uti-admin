import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  EditButton,
  Create,
  SimpleForm,
  TextInput,
  ArrayInput,
  SimpleFormIterator,
} from 'react-admin';

const HospitalsList = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <TextField source="name" />
      {/* <TextField source="" />
      <TextField source="" />
      <TextField source="" />
      <TextField source="" /> */}
      <EditButton />
    </Datagrid>
  </List>
);

const HospitalCreate = (props) => (
  <Create title={<span>Insert Hospital</span>} {...props}>
    <SimpleForm>
      <TextInput source="name" />
      <ArrayInput source="hospital-beds">
        <SimpleFormIterator>
          {/* <TextInput source="id" label="id" />
          <TextInput source="title" label="title" />
          <TextInput source="stripe" label="stripe" /> */}
        </SimpleFormIterator>
      </ArrayInput>
    </SimpleForm>
  </Create>
);

export { HospitalsList, HospitalCreate };
