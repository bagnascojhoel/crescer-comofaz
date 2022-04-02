import API_CONTEXT from '../api-context';
import { usePrivadoBaseApi } from '../privado-base-api/privado-base-api.hook';

export function usePrivadoUsuarioApi() {
  const { get, put } = usePrivadoBaseApi(API_CONTEXT.USUARIO);

  const cadastrarInteresse = async ({ idInteresses }) =>
    await put(`/cadastrar-interesses/?idInteresses=${idInteresses}`);

  const exibirMeuPerfil = async () => await get('/perfil');

  const exibirFavoritos = async () => await get('/perfil/favoritos');

  const exibirFeitos = async () => await get('/perfil/feitos');

  const editarPerfil = async ({ formData }) =>
    await put('/perfil/editar', formData);

  const exibirListas = async () => await get('/perfil/listas');

  const exibirListasSimplificadas = async ({ idPost }) =>
    await get(`/perfil/listas/${idPost}`);

  const exibirUsuarioBasico = async () => await get('/perfil/basico');

  return {
    cadastrarInteresse,
    exibirMeuPerfil,
    exibirFavoritos,
    exibirFeitos,
    editarPerfil,
    exibirListas,
    exibirListasSimplificadas,
    exibirUsuarioBasico,
  };
}
