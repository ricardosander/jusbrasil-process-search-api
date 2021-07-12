package io.github.ricardosander.jusbrasilprocessservice.uniqueprocessnumbering;

class Extractor {

  int extractYear(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 11, 7);
  }

  long extractNumberToValidate(String uniqueProcessNumbering) {
    return Long.parseLong(
        uniqueProcessNumbering.substring(0, uniqueProcessNumbering.length() - 13) +
            uniqueProcessNumbering.substring(uniqueProcessNumbering.length() - 11)
    );
  }

  int extractValidatorDigit(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 13, 11);
  }

  int extractTR(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 6, 4);
  }

  int extractJ(String uniqueProcessNumbering) {
    return extractsIntByPosition(uniqueProcessNumbering, 7, 6);
  }

  int extractNumber(String uniqueProcessNumbering) {
    return Integer.parseInt(uniqueProcessNumbering.substring(
        0,
        uniqueProcessNumbering.length() - 13
    ));
  }

  private int extractsIntByPosition(
      String uniqueProcessNumbering,
      int relativeStartPosition,
      int relativeEndPosition
  ) {
    return Integer.parseInt(
        extractsByRelativePosition(
            uniqueProcessNumbering,
            relativeStartPosition,
            relativeEndPosition
        )
    );
  }

  private String extractsByRelativePosition(
      String uniqueProcessNumbering,
      int relativeStartPosition,
      int relativeEndPosition
  ) {
    return uniqueProcessNumbering.substring(
        uniqueProcessNumbering.length() - relativeStartPosition,
        uniqueProcessNumbering.length() - relativeEndPosition
    );
  }
}
