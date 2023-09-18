
Feature: Verify Daily Mail web application

  @Web
  Scenario: Verify the video player functionality
    Given Navigate to Daily mail video page URL
    When Click the video to begin playback
    Then Click Video again for pause playback
    And Click Forward button to change the video
    And Click Back button to change the video
    Then Click Speaker icon to mute the video
    And Click Speaker icon to unmute the video
    Then Verify Autoplay is working when a video finished

    @Web
  Scenario: Check Premier League team POS
    Given Navigate to Home Daily mail page URL
    When Click the Sports menu page
    Then Scroll to Premier League table and Click View tables
    And Verify the position of the team Man Utd
