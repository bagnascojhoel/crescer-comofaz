import { useMemo } from 'react';
import { useGlobalUser } from '../../context';
import { useBaseApi } from '../base-api/base-api.hook';
import buildHeaders from '../build-headers';

export function usePrivadoBaseApi(context) {
  const [user] = useGlobalUser();

  const scope = 'privado';

  const headers = !user ? {} : buildHeaders(user);

  const baseApi = useBaseApi(`${scope}/${context}`, headers);

  return useMemo(() => baseApi, [baseApi]);
}
