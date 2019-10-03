class TDDExerciseTest extends org.scalatest.FunSuite {
  test("CubeCalculator.cube") {
    assert(TDDExercise.cube(3) === 27)
  }
}
