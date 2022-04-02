import './styles.css';
import React, { useEffect, useState } from 'react';
import { Loader, Title } from '../../components';
import { EditarInteressesActions } from './editar-interesses-actions/editar-interesses-actions.component';
import { EditarInteressesList } from './editar-interesses-list/editar-interesses-list.component';
import { useHistory, useParams } from 'react-router-dom';
import { ROUTES } from '../../../routes';
import {
  useApiErrorMessageManager,
  usePrivadoCategoriaApi,
  usePrivadoUsuarioApi,
  useToastMessageManager,
} from '../../../hooks';
import { useGlobalUser } from '../../../context';

export function EditarInteressesScreen() {
  const { selected } = useParams();
  const { push } = useHistory();
  const toastMessage = useToastMessageManager();
  const apiErrorMessage = useApiErrorMessageManager();

  const { buscarCategorias } = usePrivadoCategoriaApi();
  const { cadastrarInteresse } = usePrivadoUsuarioApi();

  const [globalUser, setGlobalUser] = useGlobalUser();
  const [idInteresses, setIdInteresses] = useState(null);
  const [categorias, setCategorias] = useState([]);

  const handleSkip = () => {
    push(ROUTES.HOME);
  };

  const handleSelection = (selected) => {
    setIdInteresses(selected);
  };

  const handleContinue = async () => {
    const response = await cadastrarInteresse({ idInteresses });

    if (response.error) {
      apiErrorMessage.send(response.error);
    } else {
      if (globalUser.primeiroAcesso) {
        toastMessage.send('Interesses definidos');
        setGlobalUser({ ...globalUser, primeiroAcesso: false });
        push(ROUTES.HOME);
      } else {
        toastMessage.send('Interesses alterados');
        push(ROUTES.EDITAR_PERFIL);
      }
    }
  };

  useEffect(() => {
    (async () => {
      const response = await buscarCategorias();

      if (response.error) apiErrorMessage.send(response.error);
      else setCategorias(response);

      if (selected) {
        const a = selected.split(',').map((v) => parseInt(v));
        setIdInteresses(a);
      }
    })();
  }, []);

  if (!categorias) return <Loader fullScreen large />;

  return (
    <div className="editar-interesses">
      <Title className="editar-interesses__title">
        Selecione seus interesses
      </Title>

      <EditarInteressesList
        value={idInteresses}
        onChange={handleSelection}
        data={categorias}
        keyExtractor={(c) => c.idCategoria}
      />

      <EditarInteressesActions
        edit={!globalUser.primeiroAcesso}
        onContinue={handleContinue}
        onSkip={handleSkip}
      />
    </div>
  );
}
