class ApplicationConstants {
  static HOME_PATH = 'http://localhost:8085';
  static API_PATH = ApplicationConstants.HOME_PATH + '/api';
  static CLIENT_API_PATH = ApplicationConstants.HOME_PATH + '/client-api';

  static DEFAULT_TAX = 0.1;
  static DEFAULT_SHIPPING_COST = 30_000;

  static DEFAULT_CLIENT_CATEGORY_PAGE_SIZE = 9;
  static DEFAULT_CLIENT_SEARCH_PAGE_SIZE = 12;
}

export default ApplicationConstants;