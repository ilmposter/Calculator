public enum ArithmeticOperations {
    ADDITION('+'),
    SUBSTRACTION('-'),
    DIVISION('/'),
    MULTIPLICATION('*');

    char operations;
    ArithmeticOperations(char operations){
        this.operations = operations;
    }
}
