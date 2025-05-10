# IP Subnet Calculator

A Java-based graphical subnet calculator that helps network administrators and students calculate network parameters for both IPv4 and IPv6 addresses.

## Features

- **Dual IP Version Support**
  - IPv4 subnet calculations
  - IPv6 subnet calculations with prefix length

- **User-Friendly GUI**
  - Modern and intuitive interface
  - Tabbed interface for separate IPv4 and IPv6 calculations
  - Responsive design that scales with window size
  - Custom font integration for enhanced visual appeal
  - Network-themed background image

- **IPv4 Calculations**
  - Network Address
  - Broadcast Address
  - Usable IP Range
  - Subnet Mask validation

- **IPv6 Calculations**
  - Network Address
  - Full IPv6 Address (expanded format)
  - Usable IP Range
  - Prefix Length support

- **Error Handling**
  - Input validation
  - User-friendly error messages
  - Robust exception handling

## Requirements

- Java Runtime Environment (JRE) 8 or higher
- Minimum screen resolution: 800x600
- graphical user interface support

## Installation

1. Clone or download the repository
2. Ensure you have Java installed on your system
3. Make sure all files are in the correct directory structure:
   ```
   IP_subnet_calculator/
   ├── IPSubnetCalculatorGUI.java
   ├── IPSubnetCalculatorLogic.java
   ├── Jedar.ttf
   ├── images/
   |   └── network_BG.jpg
   └── README.md
   ```

## Usage

1. Compile the Java files:
   ```powershell
   javac IPSubnetCalculatorGUI.java IPSubnetCalculatorLogic.java
   ```

2. Run the application:
   ```powershell
   java IPSubnetCalculatorGUI
   ```

3. For IPv4 calculations:
   - Enter an IPv4 address (e.g., 192.168.1.1)
   - Enter a subnet mask (e.g., 255.255.255.0)
   - Click "Calculate"

4. For IPv6 calculations:
   - Enter an IPv6 address (e.g., 2001:db8::1)
   - Enter the prefix length (e.g., 64)
   - Click "Calculate"

## Technical Details

- **GUI Framework**: Java Swing
- **Layout Manager**: GridBagLayout for flexible component arrangement
- **Background Processing**: SwingWorker for non-blocking calculations
- **Custom Font**: Jedar.ttf for enhanced typography
- **Network Calculations**: Java InetAddress for IP handling

## Features in Detail

### IPv4 Subnet Calculator
- Calculates network address through bitwise AND operation
- Determines broadcast address using subnet mask
- Computes usable IP range
- Validates input format and ranges

### IPv6 Subnet Calculator
- Supports compressed and full IPv6 notation
- Expands abbreviated IPv6 addresses
- Calculates network range based on prefix length
- Provides detailed subnet information

## Contributing

Feel free to fork this project and submit pull requests for any improvements you make. Some areas that could be enhanced:

- Adding CIDR notation support
- Including more network calculations
- Implementing save/load functionality for frequent calculations
- Adding export features for calculation results

## Author

Created by Tej Prakash Carpenter, Sahil Prasad, Vivek Singh, Parth Dangaya
