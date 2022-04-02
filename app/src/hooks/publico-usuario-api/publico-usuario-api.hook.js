import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';

export function usePublicoUsuarioApi() {
  const { get } = useBaseApi(`publico/${API_CONTEXT.USUARIO}`);

  const exibirPerfil = async (idUsuario) => await get(`perfil/${idUsuario}`);

  return { exibirPerfil };
}
