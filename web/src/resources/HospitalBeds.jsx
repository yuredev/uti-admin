import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  EditButton,
  ReferenceField,
  // Create,
  // SimpleForm,
  // TextInput
} from 'react-admin';

const HospitalBedsList = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <EditButton />
      <ReferenceField source="patient" reference="patients">
        <TextField source="id" />
      </ReferenceField>
    </Datagrid>
  </List>
);

export { HospitalBedsList };
