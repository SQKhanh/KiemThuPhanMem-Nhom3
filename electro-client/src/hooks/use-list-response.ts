import useAppStore from 'stores/use-app-store';
import { useQueryClient } from 'react-query';
import { ErrorMessage, ListResponse } from 'utils/FetchUtils';
import PageConfigs from 'pages/PageConfigs';

function useListResponse<O>() {
  const { queryKey } = useAppStore();

  const queryClient = useQueryClient();
  const query = queryClient.getQueryState<ListResponse<O>, ErrorMessage>(queryKey);

  const listResponse = (query && query.data) ? query.data : PageConfigs.initialListResponse as ListResponse<O>;
  const isLoading = (query) ? query.isFetching : true;

  return { listResponse, isLoading };
}

export default useListResponse;
