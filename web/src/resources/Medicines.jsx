import React from 'react';
import { List, Datagrid, TextField, EditButton } from 'react-admin';

const MedicinesList = (props) => (
  <List {...props}>
    <Datagrid>
      {/* campos do usuário buscado */}
      <TextField source="title" />
      <TextField source="stripe" />
      <EditButton />
    </Datagrid>
  </List>
);

export { MedicinesList };