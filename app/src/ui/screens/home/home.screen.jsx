import './styles.css';
import React, { useEffect, useState } from 'react';
import { ROUTES } from '../../../routes';
import { useGlobalUser } from '../../../context';
import {
  BaseButton,
  Container,
  LayoutRow,
  LightButton,
  Loader,
  OutlinedContent,
  PostList,
  VerticalSpacing,
} from '../../components/';
import {
  useApiErrorMessageManager,
  usePrivadoPostApi,
  usePublicoPostApi,
} from '../../../hooks';
import filterIcon from '../../../assets/filter.svg';
import searchIcon from '../../../assets/search.svg';

export function HomeScreen() {
  const apiErrorMessage = useApiErrorMessageManager();
  const { listarSugestoes } = usePrivadoPostApi();
  const { listarPosts } = usePublicoPostApi();

  const [globalUser] = useGlobalUser();
  const [posts, setPosts] = useState(null);

  useEffect(() => {
    (async () => {
      let response;
      if (globalUser) {
        response = await listarSugestoes(0);
      } else {
        response = await listarPosts(0);
      }

      if (response.error) {
        apiErrorMessage.send(response.error);
      } else {
        setPosts(response);
      }
    })();
  }, [globalUser]);

  if (!posts) return <Loader fullScreen large />;

  return (
    <main>
      <Container>
        <HomeSearch />
        <HomeFilter />
        <VerticalSpacing />
        <PostList dataList={posts} keyExtractor={(p) => p.idPost} />
        <VerticalSpacing />
      </Container>
    </main>
  );
}

function HomeSearch() {
  return (
    <BaseButton to={ROUTES.BUSCA} className="home-search">
      <img className="home-search__icon" src={searchIcon} alt="" />
      <OutlinedContent>
        <p className="home-search__text">O que você quer criar hoje?</p>
      </OutlinedContent>
    </BaseButton>
  );
}

function HomeFilter() {
  return (
    <>
      <VerticalSpacing />
      <LayoutRow className="home-filter" alignEnd>
        <LightButton to={ROUTES.FILTRAGEM} iconUrl={filterIcon}>
          filtrar
        </LightButton>
      </LayoutRow>
    </>
  );
}
