import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  Edit,
  Create,
  SimpleForm,
  TextInput,
} from 'react-admin';

const MedicinesList = props => (
  <List {...props}>
    <Datagrid rowClick="edit">
      <TextField source="id" />
      <TextField source="title" />
      <TextField source="stripe" />
    </Datagrid>
  </List>
);

const MedicineCreate = props => (
  <Create title={<span>Insert Medicine</span>} {...props}>
    <SimpleForm>
      <TextInput source="title" />
      <TextInput source="stripe" />
    </SimpleForm>
  </Create>
);

const MedicineEdit = props => (
  <Edit title={<span>Edit Medicine</span>} {...props}>
    <SimpleForm>
      <TextInput source="title" />
      <TextInput source="stripe" />
    </SimpleForm>
  </Edit>
)

export { MedicinesList, MedicineCreate, MedicineEdit };
