

Feature: Verify Daily Mail Mobile application

  @Mobile @Android
  Scenario: Verify the video player functionality
    Given Launch the app and go to Newspaper tab
    When Newspaper tab scroll recent issues and scroll right tap see more
    Then Tap to download 27 June edition
    #Not completed due to machine issue


   @Mobile @Android
  Scenario: Verify the downloaded Edition


