import './styles.css';
import outlinedHearthIcon from '../../../assets/outlined-hearth.svg';
import filledHearthIcon from '../../../assets/filled-hearth.svg';
import React, { useState } from 'react';
import {
  useApiErrorMessageManager,
  useAuthRequiredMessageManager,
  usePrivadoPostApi,
  useToastMessageManager,
} from '../../../hooks';
import { IconButton } from '../';
import { useGlobalUser } from '../../../context';

export function PostCover({ onRemoverFavorito, idPost, src, favorito, small }) {
  const apiErrorMessage = useApiErrorMessageManager();
  const authRequiredMessage = useAuthRequiredMessageManager();
  const toastMessage = useToastMessageManager();
  const { atualizarFavorito } = usePrivadoPostApi();

  const [globalUser] = useGlobalUser();
  const [isFavorito, setIsFavorito] = useState(favorito);

  const handleFavorito = async () => {
    if (!globalUser) {
      authRequiredMessage.handleShow();
      return;
    }

    const old = isFavorito;
    setIsFavorito(!isFavorito);
    const response = await atualizarFavorito({ idPost });

    if (response.error) {
      setIsFavorito(old);
      apiErrorMessage.send(response.error);
    } else {
      if (response) toastMessage.send('Adicionado aos favoritos');
      else toastMessage.send('Removido dos favoritos');

      if (onRemoverFavorito) onRemoverFavorito(idPost);
    }
  };

  return (
    <div className={`post-cover ${small && 'post-cover--small'}`}>
      <div
        className="post-cover__background"
        style={{ backgroundImage: `url(${src})` }}
      ></div>

      <IconButton
        size={32}
        className="post-cover__icon-button"
        iconUrl={isFavorito ? filledHearthIcon : outlinedHearthIcon}
        onClick={handleFavorito}
      />
    </div>
  );
}
