import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePublicoPostApi() {
  const { get, post } = useBaseApi(`publico/${API_CONTEXT.POST}`);

  const listarPosts = async (pagina) => await get(`?pagina=${pagina}`);

  const buscarPostNaoLogado = async (idPost) =>
    await get(`/${idPost}`);

  const listarSugestoesPorPostNaoLogado = async ({idPost}) =>
    await get(`/${idPost}/sugestoes`);

  const buscarListasPorPostNaoLogado = async ({idPost}) =>
    await get(`/${idPost}/sugestoes/listas`);

  const filtrarNaoLogado = async ({ titulo, dificuldade }) =>
    await post('/filtro', { titulo, dificuldade });

  const filtrarCategoriaNaoLogado = async ({ idCategorias }) =>
    await post(`/filtro/categoria/?idCategorias=${idCategorias}`);

  return {
    listarPosts,
    buscarPostNaoLogado,
    listarSugestoesPorPostNaoLogado,
    buscarListasPorPostNaoLogado,
    filtrarNaoLogado,
    filtrarCategoriaNaoLogado,
  };
}
