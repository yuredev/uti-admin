import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  EditButton,
  Create,
  SimpleForm,
  TextInput,
} from 'react-admin';

const MedicinesList = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <TextField source="title" />
      <TextField source="stripe" />
      <EditButton />
    </Datagrid>
  </List>
);

const MedicineCreate = (props) => (
  <Create title={<span>Insert Medicine</span>} {...props}>
    <SimpleForm>
      <TextInput source="title" />
      <TextInput source="stripe" />
    </SimpleForm>
  </Create>
);

export { MedicinesList, MedicineCreate };
