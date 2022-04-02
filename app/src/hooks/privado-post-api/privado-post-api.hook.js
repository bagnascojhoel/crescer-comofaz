import API_CONTEXT from '../api-context';
import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';

export function usePrivadoPostApi() {
  const { post, get, put } = usePrivadoBaseApi(API_CONTEXT.POST);

  const listarSugestoes = async () =>
    await get('/sugestoes');

  const listarSugestoesPorPost = async ({idPost}) =>
    await get(`${idPost}/sugestoes`);

  const buscarPost = async (idPost) =>
    await get(`/${idPost}`);

  const postar = async ({ formData }) =>
    await post('/cadastrar', formData);

  const atualizarFavorito = async ({ idPost }) =>
    await put(`/favorito/${idPost}`);

  const atualizarFeito = async ({ idPost }) =>
    await put(`/feito/${idPost}`);

  const filtrar = async ({ titulo, dificuldade }) =>
    await post('/filtro', { titulo, dificuldade });

  const filtrarCategoria = async ({ idCategorias }) =>
    await post(`/filtro/categoria/?idCategorias=${idCategorias}`);

  const editarPost = async ({ idPost, formData }) =>
    await put(`/${idPost}/editar`, formData);

  return {
    buscarPost,
    listarSugestoes,
    listarSugestoesPorPost,
    postar,
    atualizarFavorito,
    atualizarFeito,
    filtrar,
    filtrarCategoria,
    editarPost,
  };
}
