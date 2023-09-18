# new feature
# Tags: optional

Feature: Verify Daily Mail Mobile application

  Scenario: Verify the video player functionality
    Given Launch the app and go to Newspaper tab
    When Newspaper tab scroll recent issues and scroll right tap see more
    Then Tap to download 27 June edition

  Scenario: Verify the downloaded Edition

