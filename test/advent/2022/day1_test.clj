(ns advent.2022.day1-test
  (:require [advent.2022.day1 :as sut]
            [clojure.test :refer :all]))


(def smallinput "1000
2000
3000

4000
")

(def elf1 '(1000 2000 3000))

(def elf2 '(4000))

(deftest elf-list
  (testing "get a list of elfs from input"
    (is (= (sut/get-elfs smallinput) (list elf1 elf2)))))


(deftest get-most-calories
  (testing "get most"
    (is (= 6000 (sut/most-calories '((1000 2000 3000) (4000)))))))

(deftest top-three
  (testing "empty top"
    (is (= [0 3] (sut/top-three [0 0 0] '(1 2)))))
  (testing "smaller elf"
    (is (= [7 8 9] (sut/top-three [7 8 9] '(1 2)))))
  (testing "elf bigger than smallest"
    (is (= [6 7 9] (sut/top-three [5 7 9] '(6)))))
  (testing "elf bigger than second"
    (is (= [7 8 9] (sut/top-three [5 7 9] '(8)))))
  (testing "elf biggest of three"
    (is (= [7 9 10] (sut/top-three [5 7 9] '(10)))))
  (testing "add partial top"
    (is (= [5 7 9]  (sut/top-three [5 7] '(9)))))
  (testing "add to single top"
    (is (= [5 7]    (sut/top-three [5] '(7)))) )
  )

(deftest get-top-three
  (testing "reduce"
    (is (= [7 8 9]
           (sut/top-three-calories '((1) (2) (3) (9) (8) (6) (7)))))))
