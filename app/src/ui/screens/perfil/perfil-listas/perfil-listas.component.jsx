import React from 'react';
import { useHistory } from 'react-router-dom';
import { ROUTES } from '../../../../routes';
import { Container, ListGallery } from '../../../components';

export function PerfilListas({ listas }) {
  const { push } = useHistory();
  const handleListClick = (idLista) =>
    push(`${ROUTES.EXIBIR_LISTA}/${idLista}`);

  return (
    <Container>
      <ListGallery data={listas} onClickItem={handleListClick} />
    </Container>
  );
}
