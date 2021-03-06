Feature: Testing weather endpoint

  Scenario: checking weather for city by ID
    Given city ID: 524901

    When we are requesting weather data

    Then coordinates are:
      | lon | 145.77 |
      | lat | -16.92 |

#    Then lon is 145.77
#    And lat is -16.92
    And base is "stations"

    And weathers are:
      | id  | main   | description      | icon |
      | 802 | Clouds | scattered clouds | 03n  |

    And main are:
      | temp     | 300.15 |
      | pressure | 1007   |
      | humidity | 74     |
      | temp_min | 300.15 |
      | temp_max | 300.15 |

    And visibility are:
      | visibility | 10000 |

    And wind are:
      | speed | 3.6 |
      | deg   | 160 |

    And clouds are:
      | all | 40 |

    And dt are:
      | dt | 1485790200 |

    And sis are:
      | type    | 1          |
      | id      | 8166       |
      | message | 0.2064     |
      | country | AU         |
      | sunrise | 1485720272 |
      | sunset  | 1485766550 |

    And id are:
      | id | 2172797 |

    And name are:
      | name | Cairns |

    And cod are:
      | cod | 200 |
