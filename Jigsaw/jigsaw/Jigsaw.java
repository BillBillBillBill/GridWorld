package jigsaw;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

/** 在此类中填充算法，完成重拼图游戏（N-数码问题）
 * @author abe
 *
 */
public class Jigsaw {
	JigsawNode beginJNode;		// 拼图的起始状态节点
	JigsawNode endJNode;		// 拼图的目标状态节点
	JigsawNode currentJNode;	// 拼图的当前状态节点
	private Vector<JigsawNode> openList;	// open表 ：用以保存已发现但未访问的节点
	private Vector<JigsawNode> closeList;	// close表：用以保存已访问的节点
	private Vector<JigsawNode> solutionPath;// 解路径  ：用以保存从起始状态到达目标状态的移动路径中的每一个状态节点
	private boolean isCompleted;	// 完成标记：初始为false;当求解成功时，将该标记至为true
	private int searchedNodesNum;	// 已访问节点数： 用以记录所有访问过的节点的数量
	
	private double allMD = 0;
	private double allED = 0;
	private double allWrongPosAtAfterNodeNumFactor = 0;
	private int calNodeNum = 0;

	/**拼图构造函数
	 * @param bNode - 初始状态节点
	 * @param eNode - 目标状态节点
	 */
	public Jigsaw(JigsawNode bNode, JigsawNode eNode) {
		this.beginJNode = new JigsawNode(bNode);
		this.endJNode = new JigsawNode(eNode);
		this.currentJNode = new JigsawNode(bNode);
		this.openList = new Vector<JigsawNode>();
		this.closeList = new Vector<JigsawNode>();
		this.solutionPath = null;
		this.isCompleted = false;
		this.searchedNodesNum = 0;
	}

	/**此函数用于打散拼图：将输入的初始状态节点jNode随机移动len步，返回其打散后的状态节点
	 * @param jNode - 初始状态节点
	 * @param len - 随机移动的步数
	 * @return 打散后的状态节点
	 */
	public static JigsawNode scatter(JigsawNode jNode, int len) {
		int randomDirection;
		len += (int) (Math.random() * 2);
		JigsawNode jigsawNode = new JigsawNode(jNode);
		for (int t = 0; t < len; t++) {
			int[] movable = jigsawNode.canMove();
			do{randomDirection = (int) (Math.random() * 4);}
			while(0 == movable[randomDirection]);
			jigsawNode.move(randomDirection);
		}
		jigsawNode.setInitial();
		return jigsawNode;
	}

	/**获取拼图的当前状态节点
	 * @return currentJNode -  拼图的当前状态节点
	 */
	public JigsawNode getCurrentJNode() {
		return currentJNode;
	}

	/**设置拼图的初始状态节点
	 * @param jNode - 拼图的初始状态节点
	 */
	public void setBeginJNode(JigsawNode jNode) {
		beginJNode = jNode;
	}

	/**获取拼图的初始状态节点
	 * @return beginJNode - 拼图的初始状态节点
	 */
	public JigsawNode getBeginJNode() {
		return beginJNode;
	}

	/**设置拼图的目标状态节点
	 * @param jNode - 拼图的目标状态节点
	 */
	public void setEndJNode(JigsawNode jNode) {
		this.endJNode = jNode;
	}

	/**获取拼图的目标状态节点
	 * @return endJNode - 拼图的目标状态节点
	 */
	public JigsawNode getEndJNode() {
		return endJNode;
	}

	/**获取拼图的求解状态
	 * @return isCompleted - 拼图已解为true；拼图未解为false
	 */
	public boolean isCompleted() {
		return isCompleted;
	}

	/**计算解的路劲
	 * @return 若有解，则将结果保存在solutionPath中，返回true; 若无解，则返回false
	 */
	private boolean calSolutionPath() {
		if (!this.isCompleted()) {
			return false;
		} else {
			JigsawNode jNode = this.currentJNode;
			solutionPath = new Vector<JigsawNode>();
			while (jNode != null) {
				solutionPath.addElement(jNode);
				jNode = jNode.getParent();
			}
			return true;
		}
	}

	/**获取解路径文本
	 * @return 解路径solutionPath的字符串，若有解，则分行记录从初始状态到达目标状态的移动路径中的每一个状态节点；
	 * 若未解或无解，则返回提示信息。
	 */
	public String getSolutionPath() {
		String str = new String();
		str += "Begin->";
		if (this.isCompleted) {
			for (int i = solutionPath.size()-1; i>=0; i--) {
				str += solutionPath.elementAt(i).toString() + "->";
			}
			str+="End";
		} else
			str = "Jigsaw Not Completed.";
		return str;
	}

	/**获取访问过的节点数searchedNodesNum
	 * @return 返回所有已访问过的节点总数
	 */
	public int getSearchedNodesNum() {
		return searchedNodesNum;
	}
	
	/**将搜索结果写入文件中，同时显示在控制台
	 * 若搜索失败，则提示问题无解，输出已访问节点数；
	 * 若搜索成功，则输出初始状态beginJnode，目标状态endJNode，已访问节点数searchedNodesNum，路径深度nodeDepth和解路径solutionPath。
	 * @param pw - 文件输出PrintWriter类对象，如果pw为null，则写入到D://Result.txt
	 * @throws IOException
	 */
	public void printResult(PrintWriter pw) throws IOException{
		boolean flag = false;
		if(pw == null){
			pw = new PrintWriter(new FileWriter("Result.txt"));// 将搜索过程写入D://BFSearchDialog.txt
			flag = true;
		}
		if (this.isCompleted == true) {
			// 写入文件
			pw.println("Jigsaw Completed");
			pw.println("Begin state:" + this.getBeginJNode().toString());
			pw.println("End state:" + this.getEndJNode().toString());
			pw.println("Solution Path: ");
			pw.println(this.getSolutionPath());
			pw.println("Total number of searched nodes:" + this.getSearchedNodesNum());
			pw.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());

			
			// 输出到控制台
			System.out.println("Jigsaw Completed");
			System.out.println("Begin state:" + this.getBeginJNode().toString());
			System.out.println("End state:" + this.getEndJNode().toString());
			System.out.println("Solution Path: ");
			System.out.println(this.getSolutionPath());
			System.out.println("Total number of searched nodes:" + this.getSearchedNodesNum());
			System.out.println("Length of the solution path is:" + this.getCurrentJNode().getNodeDepth());

			
		} 
		else {
			// 写入文件
			pw.println("No solution. Jigsaw Not Completed");
			pw.println("Begin state:" + this.getBeginJNode().toString());
			pw.println("End state:" + this.getEndJNode().toString());
			pw.println("Total number of searched nodes:"
					+ this.getSearchedNodesNum());
			
			// 输出到控制台
			System.out.println("No solution. Jigsaw Not Completed");
			System.out.println("Begin state:" + this.getBeginJNode().toString());
			System.out.println("End state:" + this.getEndJNode().toString());
			System.out.println("Total number of searched nodes:"
					+ this.getSearchedNodesNum());
		}
		if(flag)
			pw.close();
	}

	/**探索所有与jNode邻接(上、下、左、右)且未曾被访问的节点
	 * @param jNode - 要探索的节点
	 * @return 包含所有与jNode邻接且未曾被访问的节点的Vector<JigsawNode>对象
	 */
	private Vector<JigsawNode> findFollowJNodes(JigsawNode jNode) {
		Vector<JigsawNode> followJNodes = new Vector<JigsawNode>();
		JigsawNode tempJNode;
		for(int i=0; i<4; i++){
			tempJNode = new JigsawNode(jNode);
			if(tempJNode.move(i) && !this.closeList.contains(tempJNode) && !this.openList.contains(tempJNode))
				followJNodes.addElement(tempJNode);
		}
		return followJNodes;
	}

	/**排序插入openList：按照节点的代价估值（estimatedValue）将节点插入openList中，估值小的靠前。
	 * @param jNode - 要插入的状态节点
	 */
	private void sortedInsertOpenList(JigsawNode jNode) {
		this.estimateValue(jNode);
		for (int i = 0; i < this.openList.size(); i++) {
			if (jNode.getEstimatedValue() < this.openList.elementAt(i)
					.getEstimatedValue()) {
				this.openList.insertElementAt(jNode, i);
				return;
			}
		}
		this.openList.addElement(jNode);
	}
	
	
	
	// ****************************************************************
	// *************************实验任务************************
	/**实验任务一：广度优先搜索算法，求指定3*3拼图（8-数码问题）的最优解
	 * 要求：填充广度优先搜索算法BFSearch()，执行测试脚本RunnerPart1
	 * 主要涉及函数：BFSearch()
	 */
	/**实验任务二：启发式搜索算法，求解随机5*5拼图（24-数码问题）
	 * 要求：1.修改启发式搜索算法ASearch()和代价估计函数estimateValue()，执行测试脚本RunnerPart2
	 *      2.访问节点总数不超过25000个
	 * 主要涉及函数：ASearch()，estimateValue()
	 */
	// ****************************************************************
	
	/**（实验一）广度优先搜索算法，求解指定3*3拼图（8-数码问题）的最优解。
	 * 要求函数结束后：1,isCompleted记录了求解完成状态；
	 *              2,closeList记录了所有访问过的节点；
	 *     		    3,searchedNodesNum记录了访问过的节点数；
	 *              4,solutionPath记录了解路径。
	 * @return isCompleted, 搜索成功时为true,失败为false
	 * @throws IOException
	 */
	public boolean BFSearch() throws IOException {
		// 将搜索过程写入D://BFSearchDialog.txt
		String filePath = "BFSearchDialog.txt";
		PrintWriter pw = new PrintWriter(new FileWriter(filePath));
		// *************************************
		// 用以存放某一节点的邻接节点
		Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 

		isCompleted = false;
		// (1) 将起始节点放入openList中
		openList.add(beginJNode);
		
		// (2) 如果openList为空，则搜索失败，问题无解;否则循环直到求解成功
		while (!openList.isEmpty()) {
			// (2-1)访问openList的第一个节点N，置为当前节点currentJNode
			//      若currentJNode为目标节点，则搜索成功，设置完成标记isCompleted为true，计算解路径，退出。
			currentJNode = openList.get(0);
			if (currentJNode.equals(endJNode)) {
				isCompleted = true;
				calSolutionPath();
				break;
			}
			// (2-2)从openList中删除节点N,并将其放入closeList中，表示以访问节点			
			openList.remove(0);
			closeList.add(currentJNode);
			searchedNodesNum++;
			
			// 记录并显示搜索过程
			pw.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());
			System.out.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());			

		// (2-3)寻找所有与currentJNode邻接且未曾被访问的节点，将它们按代价估值从小到大排序插入openList中
			followJNodes = findFollowJNodes(currentJNode);
			while (!followJNodes.isEmpty()) {
				openList.add(followJNodes.get(0));
				followJNodes.remove(0);
			}
			
		}
		
		// *************************************
		this.printResult(pw);
		pw.close();
		System.out.println("Record into " + filePath);
		return isCompleted;
	}
	
	/**（Demo+实验二）启发式搜索。访问节点数大于30000个则认为搜索失败。
	 * 函数结束后：isCompleted记录了求解完成状态；
	 *           closeList记录了所有访问过的节点；
	 *           searchedNodesNum记录了访问过的节点数；
	 *           solutionPath记录了解路径。
	 *  搜索过程和结果会记录在D://DemoASearchDialog.txt中。
	 * @return 搜索成功返回true,失败返回false
	 * @throws IOException
	 */
	public boolean ASearch() throws IOException{
		// 将搜索过程写入ASearchDialog.txt
		String filePath = "ASearchDialog.txt";
		PrintWriter pw = new PrintWriter(new FileWriter(filePath));
		
		// 访问节点数大于25000个则认为搜索失败
		int maxNodesNum = 25000;  
		
		// 用以存放某一节点的邻接节点
		Vector<JigsawNode> followJNodes = new Vector<JigsawNode>(); 
		
		// 重置求解完成标记为false
		isCompleted = false;           
		
		// (1)将起始节点放入openList中
		this.sortedInsertOpenList(this.beginJNode);
		
		// (2) 如果openList为空，或者访问节点数大于maxNodesNum个，则搜索失败，问题无解;否则循环直到求解成功
		while (this.openList.isEmpty() != true && searchedNodesNum <= maxNodesNum) {
			
			// (2-1)访问openList的第一个节点N，置为当前节点currentJNode
			//      若currentJNode为目标节点，则搜索成功，设置完成标记isCompleted为true，计算解路径，退出。
			this.currentJNode = this.openList.elementAt(0);
			if (this.currentJNode.equals(this.endJNode)){
				isCompleted = true;
				this.calSolutionPath();
				break;
			}
			
			// (2-2)从openList中删除节点N,并将其放入closeList中，表示以访问节点			
			this.openList.removeElementAt(0);
			this.closeList.addElement(this.currentJNode);
			searchedNodesNum++;
			
				// 记录并显示搜索过程
				pw.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());
				System.out.println("Searching.....Number of searched nodes:" + this.closeList.size() + "   Current state:" + this.currentJNode.toString());			

			// (2-3)寻找所有与currentJNode邻接且未曾被访问的节点，将它们按代价估值从小到大排序插入openList中
			followJNodes = this.findFollowJNodes(this.currentJNode);
			while (!followJNodes.isEmpty()) {
				this.sortedInsertOpenList(followJNodes.elementAt(0));
				followJNodes.removeElementAt(0);
			}
		}
		
		this.printResult(pw);	// 记录搜索结果
		pw.close(); 			// 关闭输出文件
		System.out.println("Record into " + filePath);
		return isCompleted;
	}
	
	/**（Demo+实验二）计算并修改状态节点jNode的代价估计值:f(n)=s(n)。
	 * s(n)代表后续节点不正确的数码个数
	 * @param jNode - 要计算代价估计值的节点；此函数会改变该节点的estimatedValue属性值。
	 */
	private void estimateValue(JigsawNode jNode) {
		int[] nodesState = jNode.getNodesState();
		int[] distNodesState = endJNode.getNodesState();
		int dimension = jNode.getDimension();
		double estimatevalue = 0;

		calNodeNum++;
		
		
		double wrongPosAtAfterNodeNumFactor = 3;
		double wrongPosNumFactor = 0;
		double totalManhattanDistanceOfWrongAndRightPosFactor = 5;
		double totalEuclideanDistanceOfWrongAndRightPosFactor = 4;

		
		// 后续节点不正确的数码个数
		double wrongPosAtAfterNodeNum = 0; 
		for(int index =1 ; index<dimension*dimension; index++){
			if(nodesState[index]+1 != nodesState[index+1])
				wrongPosAtAfterNodeNum++;
		}
		
		// 放错位的数码
		int wrongPosNum = 0;
		
		// 所有 放错位的数码与其正确位置的曼哈顿距离 之和
		double totalManhattanDistanceOfWrongAndRightPos = 0;
		
		// 所有 放错位的数码与其正确位置的欧几里得距离 之和
		double totalEuclideanDistanceOfWrongAndRightPos = 0;
		
		for (int i = 1; i < dimension*dimension; i++) {
			if (nodesState[i] != distNodesState[i] && nodesState[i] != 0) {
				wrongPosNum++;

//				for (int j = 1; j < dimension*dimension; j++) {
//					if (distNodesState[j] == nodesState[i]) {
//						// 曼哈顿距离
//						totalManhattanDistanceOfWrongAndRightPos += calManhattanDistance(i, j, dimension);
//						// 欧几里得距离
//						totalEuclideanDistanceOfWrongAndRightPos += calEuclideanDistance(i, j, dimension);
//						break;
//					}
//				}
				// 曼哈顿距离
				totalManhattanDistanceOfWrongAndRightPos += calManhattanDistance(i, nodesState[i], dimension);
				// 欧几里得距离
				totalEuclideanDistanceOfWrongAndRightPos += calEuclideanDistance(i, nodesState[i], dimension);
			}
		}
		if (searchedNodesNum < 5000) {
		allMD += totalManhattanDistanceOfWrongAndRightPos;
		allED += totalEuclideanDistanceOfWrongAndRightPos;
		allWrongPosAtAfterNodeNumFactor += wrongPosAtAfterNodeNum;
		double adjustMD = totalManhattanDistanceOfWrongAndRightPos / (allMD / calNodeNum);
		double adjustED = totalEuclideanDistanceOfWrongAndRightPos / (allED / calNodeNum);
		double adjustwrongPosAtAfterNodeNumFactor = wrongPosAtAfterNodeNum / (allWrongPosAtAfterNodeNumFactor / calNodeNum);
		
		
		if (adjustMD < 1 &&  0.75 < adjustMD) {
			adjustMD *= 0.75;
		} 
		if (adjustED < 1 &&  0.85 < adjustED) {
			adjustED *= 0.85;
		} 
		if (adjustwrongPosAtAfterNodeNumFactor < 1 && 0.7 < adjustwrongPosAtAfterNodeNumFactor) {
			adjustwrongPosAtAfterNodeNumFactor *= 0.9;
		} 
		
		totalManhattanDistanceOfWrongAndRightPosFactor = totalManhattanDistanceOfWrongAndRightPosFactor * adjustMD;
		totalEuclideanDistanceOfWrongAndRightPosFactor = totalEuclideanDistanceOfWrongAndRightPosFactor * adjustED;
		wrongPosAtAfterNodeNumFactor = wrongPosAtAfterNodeNumFactor * adjustwrongPosAtAfterNodeNumFactor;
		}
//		System.out.println("WrongPosAtAfterNodeNum:" + wrongPosAtAfterNodeNum);
//		//System.out.println("WrongPosNum:" + wrongPosNum);
//		System.out.println("ManhattanDistance:" + totalManhattanDistanceOfWrongAndRightPos);
//		System.out.println("EuclideanDistanceOfWrongAndRightPos:" + totalEuclideanDistanceOfWrongAndRightPos);
//		
//		System.out.println("totalManhattanDistanceOfWrongAndRightPosFactor:" + totalManhattanDistanceOfWrongAndRightPosFactor);
//		System.out.println("totalEuclideanDistanceOfWrongAndRightPosFactor:" + totalEuclideanDistanceOfWrongAndRightPosFactor);
//		System.out.println("wrongPosAtAfterNodeNumFactor:" + wrongPosAtAfterNodeNumFactor);
//		
//		
		
		estimatevalue = (int)(totalManhattanDistanceOfWrongAndRightPosFactor * totalManhattanDistanceOfWrongAndRightPos
				+ totalEuclideanDistanceOfWrongAndRightPosFactor * totalEuclideanDistanceOfWrongAndRightPos
				+ wrongPosAtAfterNodeNumFactor * wrongPosAtAfterNodeNum)
				+ wrongPosNum * wrongPosNumFactor;
		//System.out.println("estimatevalue:" + estimatevalue);
		jNode.setEstimatedValue((int)estimatevalue);
	}
	
	public int calManhattanDistance(int pos1, int pos2, int dimension) {
		int manhattanDis = 0;
		int x1 = (pos1 - 1) % dimension;
		int y1 = (pos1 - 1) / dimension;
		int x2 = (pos2 - 1) % dimension;
		int y2 = (pos2 - 1) / dimension;
		
		
		manhattanDis += Math.abs(x1 - x2);
		manhattanDis += Math.abs(y1 - y2);
		
//		System.out.println("pos1:" + pos1);
//		System.out.println("pos2:" + pos2);
//		System.out.println("manhattanDis:" + manhattanDis);
		
		return manhattanDis;
	}
	
	public double calEuclideanDistance(int pos1, int pos2, int dimension) {
		double euclideanDis = 0;
		int x1 = (pos1 - 1) % dimension;
		int y1 = (pos1 - 1) / dimension;
		int x2 = (pos2 - 1) % dimension;
		int y2 = (pos2 - 1) / dimension;
		
		
		euclideanDis += Math.sqrt(Math.abs(x1 - x2) * Math.abs(x1 - x2) + Math.abs(y1 - y2) * Math.abs(y1 - y2));
		
//		System.out.println("pos1:" + pos1);
//		System.out.println("pos2:" + pos2);
//		System.out.println("euclideanDis:" + euclideanDis);
		
		return euclideanDis;
	}

}
