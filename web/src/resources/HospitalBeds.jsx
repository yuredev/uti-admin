import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  EditButton,
  ReferenceField,
} from 'react-admin';

const HospitalBedsList = (props) => (
  <List {...props}>
    <Datagrid>
      <TextField source="id" />
      <EditButton />
      <ReferenceField source="patient_id" reference="patients">
        <TextField source="id" />
      </ReferenceField>
    </Datagrid>
  </List>
);

// const HospitalBedsCreate = (props) => (
//   <Create title={<span>Insert HospitalBed</span>} {...props}>
//     <SimpleForm>
//       <ArrayInput source="">
//         <SimpleFormIterator>
//           <TextInput source="id" label="id" />
//           <TextInput source="title" label="title" />
//           <TextInput source="stripe" label="stripe" />
//         </SimpleFormIterator>
//       </ArrayInput>
//     </SimpleForm>
//   </Create>
// );

export { HospitalBedsList };
