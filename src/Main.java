


public class Main {

    public static void main(String[] args) {

        WordAnalytics wordAnalytics = new WordAnalytics();
        int integerArgumentValue = 0;

        if (args.length == 0) {
            System.out.println("Not enough arguments");
            System.out.println(usage());
            System.exit(-1);
        } else if (args.length == 1 && args[0].equals("GenerateName")) {
            System.out.println("Didn't even flinch");
        } else if (args.length == 2) {
            switch (args[0]) {
                case "CountSpecificString":
                    wordAnalytics.CountSpecificString(args[1]);
                    break;
                case "CountAllStrings":

                    try {
                        integerArgumentValue = Integer.parseInt(args[1]);
                    }catch (NumberFormatException e){
                        System.out.println("Second argument for 'CountAllStrings' must be a number but got :'" + args[1] + "'");
                        System.exit(-1);
                    }
                    wordAnalytics.CountAllStrings(integerArgumentValue);
                    break;
                case "CountMaxString":
                    try {
                        integerArgumentValue = Integer.parseInt(args[1]);
                    }catch (NumberFormatException e){
                        System.out.println("Second argument for 'CountMaxString' must be a number but got :'" + args[1] + "'");
                        System.exit(-1);
                    }
                    wordAnalytics.CountMaxString(integerArgumentValue);
                    break;
                case "AllIncludesString":
                    wordAnalytics.AllIncludesString(args[1]);
                    break;
                default:
                    System.out.println(usage());
            }
        }
        else{
            System.out.println(usage());
        }
    }

    private static String usage(){
        StringBuilder builder = new StringBuilder();
        builder.append("Usage:\n");
        builder.append("    CountSpecificString <STRING>\n");
        builder.append("    CountAllStrings <LENGTH>\n");
        builder.append("    CountMaxString <LENGTH>\n");
        builder.append("    AllIncludesString <STRING>\n");
        builder.append("    GenerateName\n");

        return builder.toString();

    }
}
