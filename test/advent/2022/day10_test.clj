(ns advent.2022.day10-test
  (:require [advent.2022.day10 :as sut]
            [clojure.test :refer :all]
            [clojure.string :as str]))

(deftest instuction
  (testing "perform instruction should give a new state"
    (is (= [1 1 4] (sut/perform-instruction "addx 3" [ 1 ])))
    (is (= [1 1]     (sut/perform-instruction "noop"   [ 1])))))

(def simpledata
"noop
addx 3
addx -5")

(def example
"addx 15
addx -11
addx 6
addx -3
addx 5
addx -1
addx -8
addx 13
addx 4
noop
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx 5
addx -1
addx -35
addx 1
addx 24
addx -19
addx 1
addx 16
addx -11
noop
noop
addx 21
addx -15
noop
noop
addx -3
addx 9
addx 1
addx -3
addx 8
addx 1
addx 5
noop
noop
noop
noop
noop
addx -36
noop
addx 1
addx 7
noop
noop
noop
addx 2
addx 6
noop
noop
noop
noop
noop
addx 1
noop
noop
addx 7
addx 1
noop
addx -13
addx 13
addx 7
noop
addx 1
addx -33
noop
noop
noop
addx 2
noop
noop
noop
addx 8
noop
addx -1
addx 2
addx 1
noop
addx 17
addx -9
addx 1
addx 1
addx -3
addx 11
noop
noop
addx 1
noop
addx 1
noop
noop
addx -13
addx -19
addx 1
addx 3
addx 26
addx -30
addx 12
addx -1
addx 3
addx 1
noop
noop
noop
addx -9
addx 18
addx 1
addx 2
noop
noop
addx 9
noop
noop
noop
addx -1
addx 2
addx -37
addx 1
addx 3
noop
addx 15
addx -21
addx 22
addx -6
addx 1
noop
addx 2
addx 1
noop
addx -10
noop
noop
addx 20
addx 1
addx 2
addx 2
addx -6
addx -11
noop
noop
noop")

(deftest crt
  (testing "is lit?"
    (is (= "#" (sut/crt 0 1)))
    (is (= "#" (sut/crt 1 1)))
    (is (= "." (sut/crt 2 16)))
    (is (= "." (sut/crt 3 16)))
    (is (= "#" (sut/crt 4 5)))
    (is (= "#" (sut/crt 6 5)))
    (is (= "." (sut/crt 7 5)))))

(println
 (sut/solution2 example))
