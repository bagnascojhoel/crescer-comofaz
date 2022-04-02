import React, { useState } from 'react';
import { useGlobalUser } from '../../../../context';
import {
  useApiErrorMessageManager,
  useAuthRequiredMessageManager,
  usePrivadoPostApi,
  useToastMessageManager,
} from '../../../../hooks';
import { Checkbox } from '../../../components';

export function ExibirPostMarcarFeito({ idPost }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const authRequiredMessage = useAuthRequiredMessageManager();
  const toastMessage = useToastMessageManager();

  const { atualizarFeito } = usePrivadoPostApi();

  const [globalUser] = useGlobalUser();
  const [postFeito, setPostFeito] = useState(null);

  const handleMarcarComoFeito = async () => {
    if (!globalUser) {
      authRequiredMessage.handleShow();
      return;
    }

    const old = postFeito;
    setPostFeito(!postFeito);
    const response = await atualizarFeito({ idPost });

    if (response.error) {
      setPostFeito(old);
      apiErrorMessage.send(response.error);
    } else {
      if (response) toastMessage.send('Marcado como feito');
      else toastMessage.send('Desmarcado como feito');
    }
  };

  return (
    <Checkbox
      onCheck={handleMarcarComoFeito}
      text="marcar como feito"
      checked={postFeito}
    />
  );
}
