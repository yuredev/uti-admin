import React from 'react';
import { List, Datagrid, TextField, EditButton } from 'react-admin';

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

export { HospitalsList };
