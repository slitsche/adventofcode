(ns advent.2022.day2-test
  (:require [advent.2022.day2 :as sut]
            [clojure.test :refer :all]))

;;  What is the best represention

;; number of games possible
(Math/pow 3 2)


(deftest scoring
  (testing "Calc a score :rock vs :paper"
    (is (= 8 (sut/score :rock :paper)))
    (is (= 1 (sut/score :paper :rock)))
     (is (= 6 (sut/score :scissors :scissors)))
    ))

(deftest parse-line
  (testing "input line translation into model"
    (is (= [:rock :paper] (sut/parse-line "A Y")))
    (is (= [:paper :rock] (sut/parse-line "B X")))))

(deftest reverse-score-table
  (testing "reverse lookup table"
    (is (= {:a  {3 :b}} (sut/reverse-tableq {:a {:b 3}})))))

(deftest score-2
  (testing "s"
    (is (= 4 (sut/score-2 :rock 3)))
    (is (= 1 (sut/score-2 :paper 0)))
    (is (= 7 (sut/score-2 :scissors 6)))))
