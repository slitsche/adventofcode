(ns advent.2022.day6-test
  (:require [advent.2022.day6 :as sut]
            [clojure.test :refer :all]))

(deftest search
  (testing "kmp search pattern"
    (is (= 7 (sut/search "mjqjpqmgbljsphdztnvjfqwrcgsmlb" 4)))
    (is (= 5 (sut/search "bvwbjplbgvbhsrlpgdmjqwftvncz" 4)))
    (is (= 6 (sut/search "nppdvjthqldpwncqszvftbrmjlhg" 4)))
    (is (= 10 (sut/search "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 4)))
    (is (= 11 (sut/search "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" 4)))
    ))


(deftest search2
  (testing "message search longer distinct"
    (is (= 19 (sut/search "mjqjpqmgbljsphdztnvjfqwrcgsmlb" 14)))
    (is (= 23 (sut/search "bvwbjplbgvbhsrlpgdmjqwftvncz" 14)))
    (is (= 23 (sut/search "nppdvjthqldpwncqszvftbrmjlhg" 14)))
    (is (= 29 (sut/search "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg" 14)))
    (is (= 26 (sut/search "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw" 14)))
    ))
