import './styles.css';
import React from 'react';
import { CategoriaList, Container, Title } from '../../../components';

export function PerfilInteresses({ interesses }) {
  return (
    <Container>
      <Title>Interesses</Title>
      <CategoriaList
        categorias={interesses}
        className="perfil-interesses"
        forLayout
      />
    </Container>
  );
}
