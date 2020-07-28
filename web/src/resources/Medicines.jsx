import React from 'react';
import { List, Datagrid, TextField, EditButton } from 'react-admin';

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

export { MedicinesList };