import Foundation

// Complete the arrayManipulation function below.
func arrayManipulation(n: Int, queries: [[Int]]) -> Int {

    var arr = Array(repeating: 0, count: n) //initialize array

    for query in queries
    {
        var leftSide = query[0] - 1
        var rightSide = query[1] - 1
        var addedValue = query[2]

        arr[leftSide] += addedValue

        if(rightSide + 1 < arr.count)
        {
            arr[rightSide + 1] -= addedValue
        }
    }

    var maxValue = 0
    var accumulatingSum = 0

    for value in arr
    {
        accumulatingSum += value
        if(accumulatingSum > maxValue)
        {
            maxValue = accumulatingSum
        }
    }

    return maxValue
}






let stdout = ProcessInfo.processInfo.environment["OUTPUT_PATH"]!
FileManager.default.createFile(atPath: stdout, contents: nil, attributes: nil)
let fileHandle = FileHandle(forWritingAtPath: stdout)!

guard let nmTemp = readLine() else { fatalError("Bad input") }
let nm = nmTemp.split(separator: " ").map{ String($0) }

guard let n = Int(nm[0].trimmingCharacters(in: .whitespacesAndNewlines))
else { fatalError("Bad input") }

guard let m = Int(nm[1].trimmingCharacters(in: .whitespacesAndNewlines))
else { fatalError("Bad input") }

let queries: [[Int]] = AnyIterator{ readLine() }.prefix(m).map {
    let queriesRow: [Int] = $0.split(separator: " ").map {
        if let queriesItem = Int($0.trimmingCharacters(in: .whitespacesAndNewlines)) {
            return queriesItem
        } else { fatalError("Bad input") }
    }

    guard queriesRow.count == 3 else { fatalError("Bad input") }

    return queriesRow
}

guard queries.count == m else { fatalError("Bad input") }
let result = arrayManipulation(n: n, queries: queries)

fileHandle.write(String(result).data(using: .utf8)!)
fileHandle.write("\n".data(using: .utf8)!)
