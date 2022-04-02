import { useGlobalUser } from '../../context';
import API_CONTEXT from '../api-context';
import { useBaseApi } from '../base-api/base-api.hook';
import buildHeaders from '../build-headers';

export function usePrivadoAutenticacaoApi(subjectRetrieval) {
  const [globalUser] = useGlobalUser();

  const headers =
    subjectRetrieval && subjectRetrieval()
      ? buildHeaders(subjectRetrieval())
      : buildHeaders(globalUser);

  const context = `privado/${API_CONTEXT.AUTENTICACAO}`;

  const { post } = useBaseApi(context, headers);

  const autenticarUsuario = async () => await post();

  return { autenticarUsuario };
}
