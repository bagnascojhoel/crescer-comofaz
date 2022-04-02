import './styles.css';
import React, { useEffect, useState } from 'react';
import { useApiErrorMessageManager, usePrivadoPostApi } from '../../../hooks';
import {
  Container,
  InputRating,
  InputText,
  LayoutRow,
  LightButton,
  PostList,
  Title,
  VerticalSpacing,
} from '../../components';
import searchIcon from '../../../assets/search.svg';

export function BuscaScreen() {
  const apiErrorMessage = useApiErrorMessageManager();
  const { filtrar } = usePrivadoPostApi();

  const [posts, setPosts] = useState(null);
  const [titulo, setTitulo] = useState(null);
  const [dificuldade, setDificuldade] = useState(null);

  const handleTituloChange = (search) => {
    setTitulo(search);
  };

  const handleDificuldadeChange = (search) => {
    setDificuldade(search);
  };

  const handleClearTitulo = () => {
    setTitulo('');
  };

  const handleClearDificuldade = () => {
    setDificuldade(0);
  };

  useEffect(() => {
    (async () => {
      if (titulo !== null || dificuldade !== null) {
        const response = await filtrar({ titulo, dificuldade });
        if (response.error) apiErrorMessage.send(response.error);
        else setPosts(response);
      }
    })();
  }, [titulo, dificuldade]);

  return (
    <>
      <Container>
        <Title>Busca</Title>

        <LayoutRow>
          <label htmlFor="titulo">
            <img className="busca-titulo__icon" src={searchIcon} alt="" />
          </label>
          <InputText
            onChange={handleTituloChange}
            value={titulo}
            placeholder="Pizza de frigideira, plantação de ervas finas, ..."
            className="busca-titulo__input"
            id="titulo"
            autoFocus
          />
        </LayoutRow>

        {titulo && titulo !== '' ? (
          <LayoutRow alignEnd>
            <LightButton onClick={handleClearTitulo} secondary mini>
              Limpar
            </LightButton>
          </LayoutRow>
        ) : (
          <></>
        )}

        <div className="busca-dificuldade">
          <label className="busca-dificuldade__label">Dificuldade</label>
          <InputRating
            onChange={handleDificuldadeChange}
            value={dificuldade}
            className="busca-dificuldade__input"
            largeIcon
          />

          {dificuldade && dificuldade > 0 ? (
            <LayoutRow alignEnd>
              <LightButton onClick={handleClearDificuldade} secondary mini>
                Limpar
              </LightButton>
            </LayoutRow>
          ) : (
            <></>
          )}
        </div>
      </Container>

      {posts && (
        <>
          <Container>
            <PostList dataList={posts} keyExtractor={({ postId }) => postId} />
          </Container>
          <VerticalSpacing />
        </>
      )}
    </>
  );
}
