package main

import (
	"fmt"
	"go/ast"
	"go/parser"
	"go/token"
)

func main() {
	fset := token.NewFileSet()

	var filename = "/Users/mac/workspace/bean/beandev/contract-service/goone-generator/cmd/original.go"
	// Parse the original Go source code
	originalCode, err := parser.ParseFile(fset, filename, nil, parser.ParseComments)
	if err != nil {
		fmt.Println("Error parsing original code:", err)
		return
	}

	// You now have the AST of the original code in the 'originalCode' variable.
	// You can traverse the AST to analyze and generate new code.
	// Print the modified AST (optional)
	// Traverse the AST and modify/generate code as needed
	// ...

	// Print the modified AST (optional)
	ast.Print(fset, originalCode)

}
