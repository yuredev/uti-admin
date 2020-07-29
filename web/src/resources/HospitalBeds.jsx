import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  ReferenceField,
} from 'react-admin';

const HospitalBedsList = props => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <ReferenceField source="id" reference="patients" label="Patient Name">
        <TextField source="name" />
      </ReferenceField>
    </Datagrid>
  </List>
);

export { HospitalBedsList };
