import React from 'react';
import addIcon from '../../../../assets/add.svg';
import { useHistory } from 'react-router-dom';
import { useGlobalUser } from '../../../../context';
import { useAuthRequiredMessageManager } from '../../../../hooks';
import { ROUTES } from '../../../../routes';
import { LightButton } from '../../../components';

export function ExibirPostAdicionarALista({ idPost }) {
  const history = useHistory();
  const authRequiredMessage = useAuthRequiredMessageManager();

  const [globalUser] = useGlobalUser();

  const handleAdicionarPostLista = () => {
    if (!globalUser) {
      authRequiredMessage.handleShow();
      return;
    }

    history.push(`${ROUTES.ADICIONAR_POST_LISTA}/${idPost}`);
  };

  return (
    <LightButton iconUrl={addIcon} onClick={handleAdicionarPostLista}>
      adicionar
    </LightButton>
  );
}
