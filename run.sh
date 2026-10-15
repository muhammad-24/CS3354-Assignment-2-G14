
#!/usr/bin/env bash
set -euo pipefail

# Run from the repository directory
cd "$(dirname "$0")"

# Create a temporary testing directory
TEMP_DIR=$(mktemp -d)
trap 'rm -rf "$TEMP_DIR"' EXIT

mkdir -p "$TEMP_DIR/classes"

# Create a temporary ShapeManager with explicit imports
{
    echo "import edu.txst.assignment2.Shape;"
    echo "import edu.txst.assignment2.Circle;"
    echo "import edu.txst.assignment2.Square;"
    echo "import edu.txst.assignment2.Rectangle;"
    echo "import edu.txst.assignment2.Triangle;"
    cat ShapeManager.java
} > "$TEMP_DIR/ShapeManager.java"

# Compile the complete application
javac -d "$TEMP_DIR/classes" \
    src/edu/txst/assignment2/*.java \
    "$TEMP_DIR/ShapeManager.java"

# Launch the GUI
java -cp "$TEMP_DIR/classes" ShapeManager
