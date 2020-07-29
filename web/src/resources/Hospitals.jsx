import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  Create,
  SimpleForm,
  TextInput,
  ArrayInput,
  SimpleFormIterator,
} from 'react-admin';

const HospitalsList = (props) => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="name" />
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
