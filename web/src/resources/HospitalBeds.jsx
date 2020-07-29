import React from 'react';
import {
  List,
  Datagrid,
  TextField,
  Create,
  SimpleForm,
  SelectInput,
  ReferenceInput,
  TextInput,
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

const HospitalBedsCreate = props => (
  <Create title={<span>Insert Hospital Bed</span>} {...props} >
    <SimpleForm>
      <h1>Não deu</h1>
      <ReferenceInput source="patients.id" reference="patients" label="Patient id">
        <SelectInput optionText="name" />
      </ReferenceInput>   
      <h1>Não deu tbm</h1>  
      <ReferenceInput source="cpf" reference="patients" label="Patient cpf">
        <TextInput source="cpf" />
      </ReferenceInput>     
      <ReferenceInput source="name" reference="patients" label="Patient name">
        <TextInput source="name" />
      </ReferenceInput>   
      {/* <ReferenceInput source="id" reference="patients" label="Patient id">
        <TextInput source="id" />
      </ReferenceInput>   
      <ReferenceInput source="id" reference="patients" label="Patient id">
        <TextInput source="id" />
      </ReferenceInput>    */}
    </SimpleForm>
  </Create>
)

export { HospitalBedsList, HospitalBedsCreate };
