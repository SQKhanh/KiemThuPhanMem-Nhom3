import useAppStore from 'stores/use-app-store';
import NotifyUtils from 'utils/NotifyUtils';

function useManageHeaderButtonsViewModel() {
  const {
    selection,
    setActiveEntityIdsToDelete,
    setOpenedDeleteBatchEntitiesModal,
  } = useAppStore();

  const handleDeleteBatchEntitiesButton = () => {
    if (selection.length > 0) {
      setActiveEntityIdsToDelete(selection);
      setOpenedDeleteBatchEntitiesModal(true);
    } else {
      NotifyUtils.simple('Vui lòng chọn ít nhất một phần tử để xóa');
    }
  };

  return { handleDeleteBatchEntitiesButton };
}

export default useManageHeaderButtonsViewModel;
