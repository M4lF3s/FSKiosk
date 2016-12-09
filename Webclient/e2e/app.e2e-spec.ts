import { WebclientPage } from './app.po';

describe('webclient App', function() {
  let page: WebclientPage;

  beforeEach(() => {
    page = new WebclientPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
